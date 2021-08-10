# Test for [spring-cloud-sleuth #2004](https://github.com/spring-cloud/spring-cloud-sleuth/issues/2004)

In this test, I enable AdaptCachedBodyGlobalFilter for each route(refer to ApiGatewayConfiguration).

PreLogFilter is a global filter before AdaptCachedBodyGlobalFilter, while PostLogFilter is a global filter after AdaptCachedBodyGlobalFilter.

Run Main, after initialization, make a http call to http://127.0.0.1:8181/httpbin/anything, you would see the following logs:
```
2021-08-10 07:27:41.664  INFO [service-apiGateway,11a161f8cab8debc,11a161f8cab8debc] [22744] [reactor-http-nio-2][?:]: before AdaptCachedBodyGlobalFilter
2021-08-10 07:27:41.680  INFO [service-apiGateway,,] [22744] [reactor-http-nio-2][?:]: after AdaptCachedBodyGlobalFilter
```
Which indicates the span context In ThreadContext is missing after AdaptCachedBodyGlobalFilter.