import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { PlanService } from '../../services/plan.service';

@Component({
  selector: 'app-chooseplan',
  templateUrl: './chooseplan.component.html',
  styleUrls: ['./chooseplan.component.scss']
})
export class ChooseplanComponent implements OnInit {

  plans: any[] = [];
  length:number;

  constructor(private planService: PlanService) {}

  ngOnInit(): void {
    this.planService.getPlans().subscribe(
      (data: any) => {
        this.plans = data;
        console.log(this.plans);
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

}
