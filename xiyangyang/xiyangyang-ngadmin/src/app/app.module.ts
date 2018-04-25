import { CommentEditFormComponent } from './pages/cms/comment-edit-form/comment-edit-form.component';
import { CommentListComponent } from './pages/cms/comment-list/comment-list.component';
import { DocumentEditFormComponent } from './pages/cms/document-edit-form/document-edit-form.component';
import { DocumentListComponent } from './pages/cms/document-list/document-list.component';
import { CmsCategoryEditFormComponent } from './pages/cms/cms-category-edit-form/cms-category-edit-form.component';
import { CmsCategoryListComponent } from './pages/cms/cms-category-list/cms-category-list.component';
import { TopicEditFormComponent } from './pages/cms/topic-edit-form/topic-edit-form.component';
import { TopicListComponent } from './pages/cms/topic-list/topic-list.component';
import { GuessAccountListComponent } from './pages/guess/guess-account-list/guess-account-list.component';
import { GuessCategoryListComponent } from './pages/guess/guess-category-list/guess-category-list.component';
import { GuessHisAccountListComponent } from './pages/guess/guess-his-account-list/guess-his-account-list.component';
import { ProCategoryListComponent } from './pages/pro/pro-category-list/pro-category-list.component';
import { ProductListComponent } from './pages/pro/product-list/product-list.component';
import { GuessQuestionListComponent } from './pages/guess/guess-question-list/guess-question-list.component';
import { PropertyListComponent } from './pages/pro/property-list/property-list.component';
import { MemberEditFormComponent } from './pages/mem/member-edit-form/member-edit-form.component';
import { MemberListComponent } from './pages/mem/member-list/member-list.component';
import { TopicService } from './pages/cms/topic.service';
import { DocumentService } from './pages/cms/document.service';
import { CmsCategoryService } from './pages/cms/cms-category.service';
import { CommentService } from './pages/cms/comment.service';
import { GuessCategoryService } from './pages/guess/guess-category.service';
import { GuessAccountService } from './pages/guess/guess-account.service';
import { GuessHisAccountService } from './pages/guess/guess-his-account.service';
import { QuestionService } from './pages/guess/question.service';
import { MemberService } from './pages/mem/member.service';
import { MemCategoryService } from './pages/mem/mem-category.service';
import { AddressService } from './pages/mem/address.service';
import { ProCategoryService } from './pages/pro/pro-category.service';
import { ProductService } from './pages/pro/product.service';
import { PropertyService } from './pages/pro/property.service';
import { ShopService } from './pages/pro/shop.service';
import { LogService } from './pages/sys/log.service';
import { ResourcesService } from './pages/sys/resources.service';
import { RoleService } from './pages/sys/role.service';
import { UserService } from './pages/sys/user.service';
import { UserManageComponent } from './pages/sys/user-manage/user-manage.component';
import { ResourcesManageComponent } from './pages/sys/resources-manage/resources-manage.component';
import { LogManageComponent } from './pages/sys/log-manage/log-manage.component';
import { ShopManageComponent } from './pages/pro/shop-manage/shop-manage.component';
import { PropertyManageComponent } from './pages/pro/property-manage/property-manage.component';
import { ProductManageComponent } from './pages/pro/product-manage/product-manage.component';
import { ProCategoryManageComponent } from './pages/pro/pro-category-manage/pro-category-manage.component';
import { MemberManageComponent } from './pages/mem/member-manage/member-manage.component';
import { MemCategoryManageComponent } from './pages/mem/mem-category-manage/mem-category-manage.component';
import { AddressManageComponent } from './pages/mem/address-manage/address-manage.component';
import { QuestionManageComponent } from './pages/guess/question-manage/question-manage.component';
import { GuessHisAccountManageComponent } from './pages/guess/guess-his-account-manage/guess-his-account-manage.component';
import { GuessCateogryManageComponent } from './pages/guess/guess-cateogry-manage/guess-cateogry-manage.component';
import { GuessAccountManageComponent } from './pages/guess/guess-account-manage/guess-account-manage.component';
import { TopicManageComponent } from './pages/cms/topic-manage/topic-manage.component';
import { DocumentManageComponent } from './pages/cms/document-manage/document-manage.component';
import { CmsCategoryManageComponent } from './pages/cms/cms-category-manage/cms-category-manage.component';
import { CommentManageComponent } from './pages/cms/comment-manage/comment-manage.component';
import { LoginComponent } from './pages/login/login.component';
import { MainComponent } from './pages/main/main.component';

import { RouterModule, Routes, RouteReuseStrategy } from '@angular/router';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { EasyUIModule } from 'ng-easyui/components/easyui/easyui.module';
import { SimpleReuseStrategy } from './SimpleReuseStrategy';

import { AppComponent } from './app.component';
import { RoleManageComponent } from './pages/sys/role-manage/role-manage.component';
import { ProCategoryEditFormComponent } from './pages/pro/pro-category-edit-form/pro-category-edit-form.component';
import { UserEditFormComponent } from './pages/sys/user-edit-form/user-edit-form.component';
import { ShopEditFormComponent } from './pages/pro/shop-edit-form/shop-edit-form.component';
import { ProductEditFormComponent } from './pages/pro/product-edit-form/product-edit-form.component';
import { PropertyEditFormComponent } from './pages/pro/property-edit-form/property-edit-form.component';
import { GuessCategoryEditFormComponent } from './pages/guess/guess-category-edit-form/guess-category-edit-form.component';
import { GuessQuestionEditFormComponent } from './pages/guess/guess-question-edit-form/guess-question-edit-form.component';
import { GuessAccountEditFormComponent } from './pages/guess/guess-account-edit-form/guess-account-edit-form.component';
import { GuessOptionsEditFormComponent } from './pages/guess/guess-options-edit-form/guess-options-edit-form.component';
import { ShopListComponent } from './pages/pro/shop-list/shop-list.component';
import { UserListComponent } from './pages/sys/user-list/user-list.component';
import { RoleListComponent } from './pages/sys/role-list/role-list.component';
import { ResourcesListComponent } from './pages/sys/resources-list/resources-list.component';
import { RoleEditFormComponent } from './pages/sys/role-edit-form/role-edit-form.component';
import { ResourcesEditFormComponent } from './pages/sys/resources-edit-form/resources-edit-form.component';
import { LogListComponent } from './pages/sys/log-list/log-list.component';
import { AddressListComponent } from './pages/mem/address-list/address-list.component';
import { AddressEditFormComponent } from './pages/mem/address-edit-form/address-edit-form.component';
import { MemCategoryEditFormComponent } from './pages/mem/mem-category-edit-form/mem-category-edit-form.component';
import { MemCategoryListComponent } from './pages/mem/mem-category-list/mem-category-list.component';
import { ListManageComponent } from './pages/list-manage/list-manage.component';
import { HttpModule } from '@angular/http';

export const routes = [
  { path: '', redirectTo: 'index', pathMatch: 'full' },
  { path: 'index', component: MainComponent, children: [
    {path: 'ccomment', outlet: 'main', component: ListManageComponent, children: [
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: CommentListComponent},
      {path: 'new', outlet: 'list', component: CommentEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: CommentEditFormComponent}
    ]},
    {path: 'ccategory', outlet: 'main', component: ListManageComponent, children: [
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: CmsCategoryListComponent},
      {path: 'new', outlet: 'list', component: CmsCategoryEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: CmsCategoryEditFormComponent}
    ]},
    {path: 'cdocument', outlet: 'main', component: ListManageComponent, children: [
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: DocumentListComponent},
      {path: 'new', outlet: 'list', component: DocumentEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: DocumentEditFormComponent}
    ]},
    {path: 'ctopic', outlet: 'main', component: ListManageComponent, children: [
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: TopicListComponent},
      {path: 'new', outlet: 'list', component: TopicEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: TopicEditFormComponent}
    ]},
    {path: 'gaccount', outlet: 'main', component: ListManageComponent, children: [
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: GuessAccountListComponent},
      {path: 'new', outlet: 'list', component: GuessAccountEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: GuessAccountEditFormComponent}
    ]},
    {path: 'gcateogry', outlet: 'main', component: ListManageComponent, children: [
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: GuessCategoryListComponent},
      {path: 'new', outlet: 'list', component: GuessCategoryEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: GuessCategoryEditFormComponent}
    ]},
    {path: 'ghaccount', outlet: 'main', component: ListManageComponent, children:[
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: GuessHisAccountListComponent}
    ]},
    {path: 'gquestion', outlet: 'main', component: ListManageComponent, children:[
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: GuessQuestionListComponent},
      {path: 'new', outlet: 'list', component: GuessQuestionEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: GuessQuestionEditFormComponent}
    ]},
    {path: 'maddress', outlet: 'main', component: ListManageComponent, children:[
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: AddressListComponent},
      {path: 'new', outlet: 'list', component: AddressEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: AddressEditFormComponent}
    ]},
    {path: 'mcategory', outlet: 'main', component: ListManageComponent, children:[
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: MemCategoryListComponent},
      {path: 'new', outlet: 'list', component: MemCategoryEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: MemCategoryEditFormComponent}
    ]},
    {path: 'mmember', outlet: 'main', component: ListManageComponent, children:[
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: MemberListComponent},
      {path: 'new', outlet: 'list', component: MemberEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: MemberEditFormComponent}
    ]},
    {path: 'pcategory', outlet: 'main', component: ListManageComponent, children: [
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: ProCategoryListComponent},
      {path: 'new', outlet: 'list', component: ProCategoryEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: ProCategoryEditFormComponent}
    ]},
    {path: 'pproduct', outlet: 'main', component: ListManageComponent, children: [
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: ProductListComponent},
      {path: 'new', outlet: 'list', component: ProductEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: ProductEditFormComponent}
    ]},
    {path: 'pproperty', outlet: 'main', component: ListManageComponent, children: [
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: PropertyListComponent},
      {path: 'new', outlet: 'list', component: PropertyEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: PropertyEditFormComponent}
    ]},
    {path: 'pshop', outlet: 'main', component: ListManageComponent, children: [
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: ShopListComponent},
      {path: 'new', outlet: 'list', component: ShopEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: ShopEditFormComponent}
    ]},
    {path: 'slog', outlet: 'main', component: ListManageComponent, children:[
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: LogListComponent}
    ]},
    {path: 'sresources', outlet: 'main', component: ListManageComponent, children:[
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: ResourcesListComponent},
      {path: 'new', outlet: 'list', component: ResourcesEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: ResourcesEditFormComponent}
    ]},
    {path: 'srole', outlet: 'main', component: ListManageComponent, children:[
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: RoleListComponent},
      {path: 'new', outlet: 'list', component: RoleEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: RoleEditFormComponent}
    ]},
    {path: 'suser', outlet: 'main', component: ListManageComponent, children:[
      { path: '', redirectTo: 'list', pathMatch: 'full' },
      {path: 'list', outlet: 'list', component: UserListComponent},
      {path: 'new', outlet: 'list', component: UserEditFormComponent},
      {path: 'edit/:id', outlet: 'list', component: UserEditFormComponent}
    ]}
    ] },
  { path: 'login', component: LoginComponent },
  ];
  // CommentManageComponent,
  // CmsCategoryManageComponent,
  // DocumentManageComponent,
  // TopicManageComponent,
  // GuessAccountManageComponent,
  // GuessCateogryManageComponent,
  // GuessHisAccountManageComponent,
  // QuestionManageComponent,
  // AddressManageComponent,
  // MemCategoryManageComponent,
  // MemberManageComponent,
  // ProCategoryManageComponent,
  // ProductManageComponent,
  // PropertyManageComponent,
  // ShopManageComponent,
  // LogManageComponent,
  // ResourcesManageComponent,
  // RoleManageComponent,
  // UserManageComponent,
@NgModule({
  declarations: [
    LoginComponent,
    MainComponent,
    AppComponent,
    ListManageComponent,

    CmsCategoryListComponent,
    CmsCategoryEditFormComponent,
    DocumentListComponent,
    DocumentEditFormComponent,
    CommentListComponent,
    CommentEditFormComponent,
    TopicListComponent,
    TopicEditFormComponent,

    GuessAccountListComponent,
    GuessCategoryListComponent,
    GuessHisAccountListComponent,
    GuessQuestionListComponent,
    GuessCategoryEditFormComponent,
    GuessQuestionEditFormComponent,
    GuessAccountEditFormComponent,
    GuessOptionsEditFormComponent,

    ProCategoryListComponent,
    ProductListComponent,
    ShopListComponent,
    PropertyListComponent,
    ShopEditFormComponent,
    ProductEditFormComponent,
    ProCategoryEditFormComponent,
    PropertyEditFormComponent,

    UserListComponent,
    RoleListComponent,
    LogListComponent,
    ResourcesListComponent,
    UserEditFormComponent,
    RoleEditFormComponent,
    ResourcesEditFormComponent,

    AddressListComponent,
    MemCategoryListComponent,
    MemberListComponent,
    AddressEditFormComponent,
    MemCategoryEditFormComponent,
    MemberEditFormComponent,

  ],
  imports: [
    HttpModule,
    RouterModule,
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    EasyUIModule,
    RouterModule.forRoot(routes, { enableTracing: true })
  ],
  providers: [UserService,
              RoleService,
              ResourcesService,
              LogService,
              ShopService,
              PropertyService,
              ProductService,
              ProCategoryService,
              AddressService,
              MemCategoryService,
              MemberService,
              QuestionService,
              GuessHisAccountService,
              GuessCategoryService,
              GuessAccountService,
              CommentService,
              CmsCategoryService,
              DocumentService,
              TopicService,
              {provide : RouteReuseStrategy, useClass: SimpleReuseStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
