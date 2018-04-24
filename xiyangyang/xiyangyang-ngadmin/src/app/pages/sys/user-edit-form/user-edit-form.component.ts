import {Component, Input, Output, EventEmitter, OnInit} from '@angular/core';
import {Validators, FormGroup, FormBuilder, FormControl} from '@angular/forms';

@Component({
  selector: 'app-user-edit-form',
  templateUrl: './user-edit-form.component.html',
  styleUrls: ['./user-edit-form.component.css']
})
export class UserEditFormComponent implements OnInit {

  @Input() closed = true;

  _row: any;

  @Input()
  get row() {
    return this._row;
  }

  set row(value: any) {
    this._row = value;
  }

  @Input() isNewRow = false;

  @Output() save = new EventEmitter();
  @Output() cancel = new EventEmitter();


  constructor() {

  }

  ngOnInit() {
  }

  onSave() {
    // if (this.formGroup.valid) {
    //   Object.assign(this.row, this.formGroup.value);
    //   this.save.emit(this.row);
    // }

  }

  onCancel() {
    this.cancel.emit();
  }

}
