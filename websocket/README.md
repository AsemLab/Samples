## WebSocket Demo
 
Simple currencies prices app with WebSocket connection.
Connections are implemented with OkHttp & Ktor libraries. WebSocket server is developed with Ktor see [KtorServer](https://github.com/AsemLab/Kotlin-Samples/tree/main/KtorServer).

The app displays random price changes for currencies every 5 seconds. The available currencies are the dollar, the euro, and the pound sterling.

<br><br>

### Setup

Before run the app:

1. Go to [NetworkModule.kt](https://github.com/AsemLab/Samples/blob/main/websocket/src/main/java/com/asemlab/samples/websocket/di/NetworkModule.kt) and change the IP address to your machine address **Line 30**.
2. Open **cmd** and set the **WebSocket** folder as current directory.
3. Run the command `java -jar Currency.jar`
4. Now you can run the app from the Android Studio.

<br><br>

> [!IMPORTANT]  
> Do not close the **cmd** while the app is running!

<br><br>

### Screenshots

<div align="center">
<img src="https://github.com/user-attachments/assets/f5b1103b-b279-4982-92c4-ec6f64e29461" width="350"/>
<img src="https://github.com/user-attachments/assets/e2d4bfa8-6b6b-43d0-8354-f0ad55a563a6" width="350"/>
</div>
