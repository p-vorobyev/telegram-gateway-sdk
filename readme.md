# Telegram Gateway API SDK

This library allows you to verify phone numbers of users and send authorization codes through [Telegram Gateway](https://core.telegram.org/gateway).

![](https://core.telegram.org/file/400780400656/3/9iBg_m8EjJs.349165/64ba1e8722d15e124d)

## Contents
- [Requirements](#requirements)
- [Installation](#installation)
- [Example](#example)
- [License](#license)

<a name="requirements"></a>
## Requirements
| Technology | Version |
|------------|-----|
| JDK        | 21+ |
| Kotlin     | 1.9+ |

<a name="installation"></a>
## Installation
1) Download by one of two options:
- 1.1 Clone source code:
```shell
git clone https://github.com/p-vorobyev/telegram-gateway-sdk.git
```

&nbsp;&nbsp;&nbsp;&nbsp;or

- 1.2 Download artifact from GitHub Packages:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Gradle**:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Specify repository in `build.gradle.kts` with your GitHub login and personal token.

```kotlin
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/p-vorobyev/*")
        credentials {
            username = "GITHUB_LOGIN"
            password = "GITHUB_TOKEN"
        }
    }
}
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Maven**:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Specify `github` server with your credentials in `settings.xml` for Apache Maven. See GitHub [docs](https://docs.github.com/ru/enterprise-cloud@latest/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens) how to generate personal token.

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">

    <servers>
        <server>
            <id>github</id>
            <username>GITHUB_LOGIN</username>
            <password>GITHUB_TOKEN</password>
        </server>
    </servers>

</settings>
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Add repository to `pom.xml` of your project.

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/p-vorobyev/*</url>
    </repository>
</repositories>
```

3) Add dependency to your project:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Gradle**:

```kotlin
implementation("dev.voroby:telegram-gateway-sdk:1.1.0")
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Maven**:

```xml
<dependency>
    <groupId>dev.voroby</groupId>
    <artifactId>telegram-gateway-sdk</artifactId>
    <version>1.1.0</version>
</dependency>
```

<a name="example"></a>
## Example
Create `TelegramGateway` instance:

```kotlin
val protocol = Protocol.createHttpProtocol()
val telegramGateway = TelegramGateway.create(
    accessToken = "your_token",
    protocol = protocol
)
```
Available methods([documentation](https://core.telegram.org/gateway/api)):
```kotlin
interface TelegramGateway : AutoCloseable {

    suspend fun checkSendAbility(
        request: CheckSendAbility.Request
    ): Either<Throwable, StatusResponse>

    suspend fun checkVerificationStatus(
        request: CheckVerificationStatus.Request
    ): Either<Throwable, StatusResponse>

    suspend fun sendVerificationMessage(
        request: SendVerificationMessage.Request
    ): Either<Throwable, StatusResponse>

    suspend fun revokeVerificationMessage(
        request: RevokeVerificationMessage.Request
    ): Either<Throwable, BooleanResponse>
}
```
Let's check the ability to send a verification message to the specified phone number:
```kotlin
telegramGateway.use { tg ->
    val request = CheckSendAbility.Request("phone_number_in_international_format")
    val statusResponse: Either<Throwable, StatusResponse> = tg.checkSendAbility(request)
    statusResponse.fold(
        { println(it.stackTraceToString()) },
        { onProtocolSuccess(it) }
    )
}
```

<a name="license"></a>
## License
[MIT License](https://github.com/p-vorobyev/telegram-gateway-sdk/blob/master/LICENSE)