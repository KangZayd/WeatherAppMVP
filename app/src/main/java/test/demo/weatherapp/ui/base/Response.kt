package test.demo.weatherapp.ui.base

sealed class Response {
    data class OnLoading(var showLoading : Boolean) : Response()
    data class SuccessResponse(var s : Any):Response()
    data class ErrorResponse(var s : String?) : Response()
}
