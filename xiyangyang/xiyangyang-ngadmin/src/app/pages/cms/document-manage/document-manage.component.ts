import { DocumentService } from './../document.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-document-manage',
  templateUrl: './document-manage.component.html',
  styleUrls: ['./document-manage.component.css']
})
export class DocumentManageComponent implements OnInit {

  constructor(private service: DocumentService) { }

  ngOnInit() {
  }

}
