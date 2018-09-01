package test.gojek.gojektest.ui.base

sealed class Response {

    data class SuccessResponse(var s : String):Response()
    data class ErrorResponse(var s : String) : Response()
}