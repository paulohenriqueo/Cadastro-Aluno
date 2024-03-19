import { Component } from '@angular/core';
import { Studant } from '../student';
import { FormBuilder, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.css'
})
export class ClientsComponent {
      students: Studant[] = [];

      formGroupStudent : FormGroup;

      constructor(private formBuilder: FormBuilder){
        this.formGroupStudent = formBuilder.group({
          id : [''],
          name : [''],
          course : [''],
        });
      }
      save(){
        this.students.push(this.formGroupStudent.value);
      }
}
