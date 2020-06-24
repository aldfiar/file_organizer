package organizer

import better.files.File
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.StrictLogging
import organizer.collection.{CollectionType, FileCollection}

import scala.collection.JavaConverters._

object Main extends StrictLogging {
  def main(args: Array[String]): Unit = {
    val default = ConfigFactory.parseResources("organizer.conf").resolve()
    val paths = default.getConfig("paths")

    val types = default.getConfig("types")
    val typeSet = types.entrySet().asScala
    logger.info("Registered {} types", typeSet.size)

    val extensionsConfig = default.getConfigList("extensions")
    val contentTypes = extensionsConfig.asScala.map(x => CollectionType(name = x.getString("type"), extensions = x.getStringList("ext").asScala.toList))
    logger.info("Registered extensions: {}", contentTypes.size)

    val foldersConfig = paths.getConfigList("folders")
    val uncheckedFolders = foldersConfig.asScala.map(x => {
      val folderType = x.getString("type")
      val path = x.getString("path")
      val res = contentTypes.filter(z => z.name.equals(folderType)).head
      new FileCollection(root = File(path), affiliation = res)
    })
    logger.info(s"Get ${uncheckedFolders.length} folders from configuration file")
    val folders = uncheckedFolders.filter(x => x.exists())
    folders.foreach(x => logger.info(s"Folder: ${x.path()}"))
  }
}
