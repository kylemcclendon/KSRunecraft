package ksrunecraft

import java.io.File

import org.bukkit.plugin.java.JavaPlugin
import runecraft.RunecraftTeles

class KSRunecraft extends JavaPlugin {
  var dFolder: Option[File] = None

  private var rune: RunecraftTeles = null

  override def onEnable() = {
    val pm = getServer.getPluginManager
    dFolder = Option(getDataFolder)

    if (dFolder.isEmpty) {
      getDataFolder.mkdirs()
      dFolder = Option(getDataFolder)
    }

    rune = new RunecraftTeles(dFolder.get)
    pm.registerEvents(rune, this)
  }

  override def onDisable() {
    rune.writeToFiles()
    getLogger.info("Kadmin disabled")
  }
}
