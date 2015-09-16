package local.indare.gitbucket.plugin.hook.controller

import local.arino.test.html
import gitbucket.core.controller.ControllerBase
import gitbucket.core.service.{RepositoryService, AccountService}
import gitbucket.core.util.AdminAuthenticator
import jp.sf.amateras.scalatra.forms._
import org.slf4j.LoggerFactory


class HookController extends HookControllerBase
with AdminAuthenticator with RepositoryService

trait HookControllerBase extends ControllerBase with AccountService with RepositoryService{
  self: AdminAuthenticator =>

  private val logger = LoggerFactory.getLogger(classOf[HookController])

  case class ScriptForm(hookname: String, script: String)

  private val scriptForm = mapping(
    "hookname" -> trim(label("Hook Name", text(required))),
    "script" -> trim(label("Hook Script", text(required)))
  )(ScriptForm.apply)

  get("/admin/hookscript"){
    html.test(flash.get("info"))
  }

}