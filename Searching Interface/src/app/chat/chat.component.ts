import { Component, OnInit } from "@angular/core";
import * as Stomp from "stompjs";
import * as SockJS from "sockjs-client";
import * as $ from "jquery";
import { MatIconModule } from "@angular/material";

declare var speechObject: any;
declare function recordAndRecognize(): void;
declare function synthesizeSpeech(text): void;
declare function getResult(): string;
declare function clearResult();

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})

export class ChatComponent implements OnInit {
  ngOnInit(): void {
    this.toggleVisbility = true;
  }
  toggleVisbility : any;
  value: string;
  private serverUrl = "http://172.23.238.183:8080/socket";
  private title = "ChatBot Interface";
  private stompClient;
  private typedvalue;
  private sessionId;
  private ws;
  chatInterface = false;
  searchResult = true;
  result: any;
  leftValue = 0;
  rightValue = 5;
  recommendation: any;
  resttingTheValue() {
    this.leftValue = 0;
    this.rightValue = 5;
  }

  toggleView() {
    if (this.chatInterface) {
      this.chatInterface = false;
      this.searchResult = true;
    } else {
      this.chatInterface = true;
      this.searchResult = false;
    }
  }

  constructor() {
    this.initializeWebSocketConnection();
  }

  fakeCallBack() {
    // this.message=message.path;
    setTimeout(() => {
      const voiceRes = getResult();
      if (voiceRes === undefined || voiceRes === "") {
        this.fakeCallBack();
      } else {
        console.log(voiceRes);
        this.value = getResult();
        this.sendMessage(getResult(), null);
        clearResult();
      }
    }, 500);
  }

  initializeWebSocketConnection() {
    this.ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(this.ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      this.sessionId = /\/([^\/]+)\/websocket/.exec(this.ws._transport.url)[1];
      that.stompClient.subscribe("/chat/" + this.sessionId, message => {
        if (message.body) {
          var responseDiv = document.createElement("div");
          responseDiv.className = "message";
          responseDiv.style.cssText =
            "display: block; background-color: lightgreen; border: 1px solid red; border-radius: 5px; width: 70%; padding: 10px;  overflow: hidden;";
          responseDiv.innerHTML =
            responseDiv.innerHTML + "Bot : " + message.body;
          document.getElementsByClassName("chat")[0].appendChild(responseDiv);
          // $(".message").append("<div>"+'Bot : '+  message.body + "</div>");
          // console.log(message.body);
          synthesizeSpeech(message.body);
          console.log("session id " + this.sessionId);
          // if (message.body == "I can help you with that") {
          // while(this.result==null){
          // }
          // }
        }
        // console.log(/SESS\w*ID=([^;]+)/i.test(document.cookie) ? RegExp.$1 : false);
      });
      that.stompClient.subscribe("/search/" + this.sessionId, message => {
        console.log("message received", message);
        that.result = JSON.parse(message.body).response;
        console.log("final result is this", that.result);
        if(that.chatInterface == false) {
          that.toggleView();
          that.toggleVisbility = false;
        }
        that.resttingTheValue();
      });
      that.stompClient.subscribe(
        "/recommendation/" + this.sessionId,
        message => {
          // console.log(message);
          that.recommendation = JSON.parse(message.body).intentRecommendations;
          console.log("final recommendation is this", that.recommendation);
          // that.toggleView();
        }
      );
    });
  }

  ngOnChanges() {
    console.log("new value ", this.result);
  }

  public startRecP5(event: Event) {
    event.preventDefault();
    recordAndRecognize();
    console.log("this is called");
    setTimeout(() => {
      this.fakeCallBack();
      500;
    });
    // this.fakeCallBack();
  }

  sendMessage(message, event: Event) {
    var userDiv = document.createElement("div");
    userDiv.className = "typed";
    userDiv.style.cssText =
      "display: block;background: white; overflow: hidden; padding: 10px; border: 1px solid red;  border-radius: 5px; margin-left: 30%;  text-align: right; ";
    userDiv.innerHTML =
      userDiv.innerHTML +
      "<span> Me : </span>" +
      // "<mat-card><mat-card-header><mat-card-subtitle> Me </mat-card-subtitle></mat-card-header><mat-card-content>" +
      message +
      "</mat-card-content></mat-card>";
    document.getElementsByClassName("chat")[0].appendChild(userDiv);
    // $("#chat1").val + "something";
    console.log(message);
    // synthesizeSpeech(message);
    if (event != null) {
      event.preventDefault();
    }
    this.stompClient.send(
      "/app/send/message",
      {},
      '{"message":' +
        '"' +
        message +
        '"' +
        ',"sessionId":' +
        '"' +
        /\/([^\/]+)\/websocket/.exec(this.ws._transport.url)[1] +
        '"' +
        "}"
    );
    $("#input").val("");
  }

  goLeft() {
    if (this.leftValue > 0) {
      this.rightValue = this.leftValue;
      this.leftValue = this.leftValue - 5;
    }
  }

  goRight() {
    if (this.rightValue < 40) {
      this.leftValue = this.rightValue;
      this.rightValue = this.rightValue + 5;
    }
  }

  sendRecommendation(number, event: Event) {
    if (event != null) {
      event.preventDefault();
    }
    // console.log(this.recommendation.get(number).domainRecommend);
    this.stompClient.send(
      "/app/send/recommendation",
      {},
      '{"message":' +
        '"' +
        this.recommendation[number].domainRecommend +
        " " +
        this.recommendation[number].conceptRecommend +
        '",' +
        '"intent": "' + this.recommendation[number].intentRecommend +
        '"' +
        ',"sessionId":' +
        '"' +
        /\/([^\/]+)\/websocket/.exec(this.ws._transport.url)[1] +
        '"' +
        "}"
    );
    $("#input").val("");
  }
}
