package test.gojek.gojektest.ui.base

sealed class Response {
    data class OnLoading(var showLoading : Boolean) : Response()
    data class SuccessResponse(var s : Any):Response()
    data class ErrorResponse(var s : String?) : Response()
}
