package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .setDatabaseUrl(dbUrl)
        .build()

    var db = FirebaseApp.initializeApp(options)

    val message1 = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    val res1 = FirebaseMessaging.getInstance().send(message1)

    val message3 = Message.builder()
        .putData("action", "DISLIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    val res3 = FirebaseMessaging.getInstance().send(message3)

    val message2 = Message.builder()
        .putData("action", "NEW_POST")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "author": "Netology",
          "content": "Something new about education
          > Task :compileKotlin UP-TO-DATE
          > Task :compileJava NO-SOURCE
          > Task :processResources NO-SOURCE
          > Task :classes UP-TO-DATE",
          "published": "today",
          "likes": 1,
          "shares": 2,
          "views": 3
        }""".trimIndent())
        .setToken(token)
        .build()

    val res2 = FirebaseMessaging.getInstance().send(message2)

    println(res2)
}
