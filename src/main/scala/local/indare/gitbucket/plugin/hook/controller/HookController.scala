package local.indare.gitbucket.plugin.hook.controller

import local.indare.gitbucket.plugin.hook.html
import gitbucket.core.controller.ControllerBase
import gitbucket.core.service.{RepositoryService, AccountService, SystemSettingsService}
import gitbucket.core.util.AdminAuthenticator
import jp.sf.amateras.scalatra.forms._
import org.slf4j.LoggerFactory


class HookController extends HookControllerBase
with AdminAuthenticator with RepositoryService with AccountService

trait HookControllerBase extends ControllerBase with RepositoryService{
  self: AdminAuthenticator with RepositoryService with AccountService =>

  private val logger = LoggerFactory.getLogger(classOf[HookController])

  case class ScriptForm(repo: String, hooktype: String, script: String)

  private val scriptForm = mapping(
    "repo" -> trim(label("Repository", text(required))),
    "hooktype" -> trim(label("Hook Type", text(required))),
    "script" -> trim(label("Script", text(required)))
  )(ScriptForm.apply)

  get("/admin/hook")(adminOnly {

    html.hook(flash.get("info"))

  })

}