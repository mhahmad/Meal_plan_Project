import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { Answer } from "src/app/mealplan/models/Answers";
import { RegisterService } from "src/app/mealplan/services/register.service";

@Component({
  selector: "app-onboarding8",
  template: `
    <style>
      .box:hover {
        background: #e09167;
      }
    </style>

    <div class="container">
      <div class="top">
        <p class="question">Are you at risk of any of the following?</p>
        <p class="text">Select which apply to you</p>
        <p class="line"></p>
      </div>
      <div class="content">
        <!-- box1 -->
        <div
          class="box-answers"
          (click)="enableDisableRule1(allAnswers[0])"
          [ngClass]="{ green: toggle1, white: !toggle1 }"
        >
          <div class="text">{{ allAnswers[0].text }}</div>
        </div>

        <!-- box2 -->
        <div
          class="box-answers"
          (click)="enableDisableRule2(allAnswers[1])"
          [ngClass]="{ green: toggle2, white: !toggle2 }"
        >
          <div class="text">{{ allAnswers[1].text }}</div>
        </div>

        <!-- box3 -->
        <div
          class="box-answers"
          (click)="enableDisableRule3(allAnswers[2])"
          [ngClass]="{ green: toggle3, white: !toggle3 }"
        >
          <div class="text">{{ allAnswers[2].text }}</div>
        </div>
        <!-- box4 -->
        <div
          class="box-answers"
          (click)="enableDisableRule4(allAnswers[3])"
          [ngClass]="{ green: toggle4, white: !toggle4 }"
        >
          <div class="text">{{ allAnswers[3].text }}</div>
        </div>
        <!-- box5 -->
        <div
          class="box-answers"
          (click)="enableDisableRule5(allAnswers[4])"
          [ngClass]="{ green: toggle5, white: !toggle5 }"
        >
          <div class="text">{{ allAnswers[4].text }}</div>
        </div>
      </div>
    </div>
  `,
  styleUrls: ["./onboarding8.component.scss"],
})
export class Onboarding8Component implements OnInit {
  toggle1: boolean;
  toggle2: boolean;
  toggle3: boolean;
  toggle4: boolean;
  toggle5: boolean;
  valid: boolean;
  @Output() sendData = new EventEmitter<boolean>();

  allAnswers: Answer[];
  userAnswers: Answer[] = [];

  constructor(private registerSrv: RegisterService) {
    this.toggle1 = false;
    this.toggle2 = false;
    this.toggle3 = false;
    this.toggle4 = false;
    this.toggle5 = false;
    this.valid = true;

    this.allAnswers = [
      { index: 0, text: "Type 2 Diabetes" },
      { index: 1, text: "High Cholesterol" },
      { index: 2, text: "High Blood Pressure" },
      { index: 3, text: "Heart Disease" },
      { index: 4, text: "Asthma" },
    ];
  }

  ngOnInit(): void {}

  enableDisableRule1(value: Answer) {
    this.toggle1 = !this.toggle1;
    this.addOrRemoveSelection(value, this.toggle1);
    this.validation();
  }
  enableDisableRule2(value: Answer) {
    this.toggle2 = !this.toggle2;
    this.addOrRemoveSelection(value, this.toggle2);
    this.validation();
  }
  enableDisableRule3(value: Answer) {
    this.toggle3 = !this.toggle3;
    this.addOrRemoveSelection(value, this.toggle3);
    this.validation();
  }
  enableDisableRule4(value: Answer) {
    this.toggle4 = !this.toggle4;
    this.addOrRemoveSelection(value, this.toggle4);
    this.validation();
  }
  enableDisableRule5(value: Answer) {
    this.toggle5 = !this.toggle5;
    this.addOrRemoveSelection(value, this.toggle5);
    this.validation();
  }

  addOrRemoveSelection(answer: Answer, isSelected: boolean) {
    if (!this.registerSrv.getUserInfo().medicalRisk) {
      this.registerSrv.getUserInfo().medicalRisk = "";
    }

    if (isSelected) {
      this.userAnswers.push(answer);
    } else {
      const idx = answer.index;
      this.userAnswers = this.userAnswers.filter((obj) => obj.index !== idx); //remove from array
    }

    let currentAnswers: string[] = [];
    this.userAnswers.map((ans) => {
      currentAnswers?.push(ans.text);
    });
    const currentAnswersStr: string = currentAnswers?.join(","); //saving answers array as a single text separated by comma (,)
    this.registerSrv.getUserInfo().medicalRisk = currentAnswersStr;

    // console.log(this.registerSrv.getUserInfo().medicalRisk);
  }

  validation(): any {
    if ( this.toggle1 || this.toggle2 || this.toggle3 || this.toggle4 || this.toggle5){
      this.valid = true;
      console.log("Valid")
    }
    else {
      this.valid = true;
      console.log("valid")
    }
     this.sendData.emit(this.valid);
  }

}
