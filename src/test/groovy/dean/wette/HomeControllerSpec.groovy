package dean.wette

import com.amazonaws.services.lambda.runtime.ClientContext
import com.amazonaws.services.lambda.runtime.CognitoIdentity
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import io.micronaut.function.aws.proxy.payload1.ApiGatewayProxyRequestEventFunction
import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpStatus
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class HomeControllerSpec extends Specification {
    @Shared
    @AutoCleanup
    ApiGatewayProxyRequestEventFunction handler = new ApiGatewayProxyRequestEventFunction()

    void "test handler"() {
        given:
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent()
        request.path = "/"
        request.httpMethod = HttpMethod.GET.toString()

        when:
        APIGatewayProxyResponseEvent response = handler.handleRequest(request, lambdaContext)
        then:
        HttpStatus.OK.code == response.statusCode.intValue()
        "{\"message\":\"Hello World\"}" == response.getBody()
    }

    static final Context lambdaContext = new Context() {

        @Override
        String getAwsRequestId() {
            return null
        }

        @Override
        String getLogGroupName() {
            return null
        }

        @Override
        String getLogStreamName() {
            return null
        }

        @Override
        String getFunctionName() {
            return null
        }

        @Override
        String getFunctionVersion() {
            return null
        }

        @Override
        String getInvokedFunctionArn() {
            return null
        }

        @Override
        CognitoIdentity getIdentity() {
            return null
        }

        @Override
        ClientContext getClientContext() {
            return null
        }

        @Override
        int getRemainingTimeInMillis() {
            return 0
        }

        @Override
        int getMemoryLimitInMB() {
            return 0
        }

        @Override
        LambdaLogger getLogger() {
            return null
        }
    }
}