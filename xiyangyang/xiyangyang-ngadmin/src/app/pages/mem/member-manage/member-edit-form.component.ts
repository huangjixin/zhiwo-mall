// import {Component, Input, Output, EventEmitter} from '@angular/core';
// import {Validators, FormGroup, FormBuilder, FormControl} from '@angular/forms';
//
// @Component({
//   selector: 'member-edit-form',
//   template: `
//         <eui-dialog [closed]="closed" [draggable]="true" [modal]="true" [title]="isNewRow ? 'Add' : 'Edit'">
//             <form novalidate [formGroup]="formGroup" style="padding:20px 40px">
//                 <div style="margin-bottom:20px">
//                     <label [for]="name">Item ID:</label>
//                     <eui-textbox #itemid formControlName="itemid" [invalid]="formGroup.get('itemid').invalid" style="width:200px"></eui-textbox>
//                     <div class="error" *ngIf="formGroup.get('itemid').hasError('required')">This field is required.</div>
//                 </div>
//                 <div style="margin-bottom:20px">
//                     <label [for]="name">Name:</label>
//                     <eui-textbox #name formControlName="name" [invalid]="formGroup.get('name').invalid" style="width:200px"></eui-textbox>
//                     <div class="error" *ngIf="formGroup.get('name').hasError('required')">This field is required.</div>
//                 </div>
//                 <div style="margin-bottom:20px">
//                     <label [for]="listprice">List Price:</label>
//                     <eui-numberbox #listprice formControlName="listprice" precision="1" style="width:200px"></eui-numberbox>
//                 </div>
//                 <div style="margin-bottom:20px">
//                     <label [for]="unitcost">Unit Price:</label>
//                     <eui-numberbox #unitcost formControlName="unitcost" style="width:200px"></eui-numberbox>
//                 </div>
//                 <div style="margin-bottom:20px">
//                     <label [for]="attr">Attribute:</label>
//                     <eui-textbox #attr formControlName="attr" style="width:200px"></eui-textbox>
//                 </div>
//             </form>
//             <div class="dialog-button">
//                 <eui-linkbutton (click)="onSave()" style="width:80px">Save</eui-linkbutton>
//                 <eui-linkbutton (click)="onCancel()" style="width:80px">Cancel</eui-linkbutton>
//             </div>
//         </eui-dialog>
//     `,
//   styles: [`
//         .error{
//             color: red;
//             font-size: 12px;
//             margin: 4px 0 4px 80px;
//         }
//     `]
// })
// export class MemberEditFormComponent {
//   @Input() closed = true;
//
//   _row: any;
//
//   @Input()
//   get row() {
//     return this._row;
//   }
//
//   set row(value: any) {
//     this._row = value;
//     this.formGroup.reset(this._row);
//   }
//
//   @Input() isNewRow = false;
//
//   @Output() save = new EventEmitter();
//   @Output() cancel = new EventEmitter();
//
//   formGroup: FormGroup;
//
//   constructor(fb: FormBuilder) {
//     this.formGroup = fb.group({
//       'itemid': [null, Validators.required],
//       'name': [null, Validators.required],
//       'listprice': null,
//       'unitcost': null,
//       'attr': null
//     });
//   }
//
//   onSave() {
//     if (this.formGroup.valid) {
//       Object.assign(this.row, this.formGroup.value);
//       this.save.emit(this.row);
//     }
//
//   }
//
//   onCancel() {
//     this.cancel.emit();
//   }
// }
