import { Component,OnInit } from '@angular/core';
import { DietType } from 'src/app/mealplan/models/DietType';
import { DayMealService } from 'src/app/mealplan/services/day-meal.service';


@Component({
  selector: 'app-diet-type',
  templateUrl: './diet-type.component.html',
  styleUrls: ['./diet-type.component.scss']
})
export class DietTypeComponent implements OnInit {
  keto=true;
  vegan=true;
  gluten=true;
  dairy=true;
  mealDietType:DietType[];
  constructor(private dayMealService:DayMealService) { }

  ngOnInit(): void {
    this.getMealDietType();
    this.mealDietTypeConditions();
  }


  public getMealDietType () {
    this.mealDietType=this.dayMealService.getMealDietType();
  }

  public mealDietTypeConditions(){
    for(let i=0;i<this.mealDietType.length;i++)
    {
    if(this.mealDietType[i].text=="keto"){
      this.keto=true;
    }
    if(this.mealDietType[i].text=="vegan"){
      this.vegan=true;
    }
    if(this.mealDietType[i].text=="gluten"){
      this.gluten=true;
    }
    if(this.mealDietType[i].text=="dairy"){
      this.dairy=true;
    }
  }
  }

}