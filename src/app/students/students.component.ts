import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Student } from '../student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrl: './students.component.css'
})
export class StudentsComponent implements OnInit {
  students: Student[] = [];
  formGroupStudent : FormGroup;
  isEditing: boolean = false;

      constructor(private formBuilder: FormBuilder,
                  private service: StudentService){
        this.formGroupStudent = formBuilder.group({
          id : [''],
          name : [''],
          course : [''],
        });
      }
  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents(){
    this.service.getStudents().subscribe({
      next: data => this.students = data
    });
  }
  save(){
    if (this.isEditing) {
      this.service.update(this.formGroupStudent.value).subscribe({
        next : () => {
          this.loadStudents();
          this.isEditing = false;
        }
      })
    }
    else{
      this.service.save(this.formGroupStudent.value).subscribe({
        next: data => this.students.push(data)
    });
    }
    this.formGroupStudent.reset();
  }
  delete(student:Student){
    this.service.delete(student).subscribe({
      next: () => this.loadStudents()
    });
  }
  edit(student:Student){
    this.formGroupStudent.setValue(student);
    this.isEditing = true;
  }
}
