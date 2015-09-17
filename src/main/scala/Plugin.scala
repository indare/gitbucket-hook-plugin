import javax.servlet.ServletContext

import gitbucket.core.plugin.PluginRegistry
import gitbucket.core.service.SystemSettingsService.SystemSettings
import gitbucket.core.util.Version
import local.indare.gitbucket.plugin.hook.controller.HookController

class Plugin extends gitbucket.core.plugin.Plugin {
  override val pluginId: String = "hook plugin"

  override val pluginName: String = "Hook Plugin"

  override val description: String = "Setting for remote repository hook script plugin"

  override val versions: List[Version] = List(
    Version(0, 1)
  )

  override def javaScripts(registry: PluginRegistry, context: ServletContext, settings: SystemSettings): Seq[(String, String)] = {
    // Add Menu link to side menu
    val path = settings.baseUrl.getOrElse(context.getContextPath)

    Seq(
      ".*/admin/(?!hook).*" -> s"""
                                      |$$('.nav.nav-tabs.nav-stacked.side-menu>li:last').after(
                                      |  $$('<li><a href="${path}/admin/hook">Hook Scripts</a></li>')
                                      |);
      """.stripMargin,
    ".*/admin/hook" -> s"""
                                      |$$('.nav.nav-tabs.nav-stacked.side-menu>li:last').after(
                                      |  $$('<li class="active"><a href="${path}/admin/hook">Hook Scripts</a></li>')
                                      |);
      """.stripMargin
    )
  }


  override val controllers = Seq(
    "/admin/hook" -> new HookController()
  )

}
