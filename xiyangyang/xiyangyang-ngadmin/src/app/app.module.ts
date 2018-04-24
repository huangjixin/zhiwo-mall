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

export const routes = [
  { path: '', redirectTo: 'index', pathMatch: 'full' },
  { path: 'index', component: MainComponent, children: [
    {path: 'ccomment', outlet: 'main', component: CommentManageComponent},
    {path: 'ccategory', outlet: 'main', component: CmsCategoryManageComponent},
    {path: 'cdocument', outlet: 'main', component: DocumentManageComponent},
    {path: 'ctopic', outlet: 'main', component: TopicManageComponent},
    {path: 'gaccount', outlet: 'main', component: GuessAccountManageComponent},
    {path: 'gcateogry', outlet: 'main', component: GuessCateogryManageComponent},
    {path: 'ghaccount', outlet: 'main', component: GuessHisAccountManageComponent},
    {path: 'gquestion', outlet: 'main', component: QuestionManageComponent},
    {path: 'maddress', outlet: 'main', component: AddressManageComponent},
    {path: 'mcategory', outlet: 'main', component: MemCategoryManageComponent},
    {path: 'mmember', outlet: 'main', component: MemberManageComponent},
    {path: 'pcategory', outlet: 'main', component: ProCategoryManageComponent},
    {path: 'pproduct', outlet: 'main', component: ProductManageComponent},
    {path: 'pproperty', outlet: 'main', component: PropertyManageComponent},
    {path: 'pshop', outlet: 'main', component: ShopManageComponent},
    {path: 'slog', outlet: 'main', component: LogManageComponent},
    {path: 'sresources', outlet: 'main', component: ResourcesManageComponent},
    {path: 'srole', outlet: 'main', component: RoleManageComponent},
    {path: 'suser', outlet: 'main', component: UserManageComponent}
    ] },
  { path: 'login', component: LoginComponent },
  ];

@NgModule({
  declarations: [
    CommentManageComponent,
    CmsCategoryManageComponent,
    DocumentManageComponent,
    TopicManageComponent,
    GuessAccountManageComponent,
    GuessCateogryManageComponent,
    GuessHisAccountManageComponent,
    QuestionManageComponent,
    AddressManageComponent,
    MemCategoryManageComponent,
    MemberManageComponent,
    ProCategoryManageComponent,
    ProductManageComponent,
    PropertyManageComponent,
    ShopManageComponent,
    LogManageComponent,
    ResourcesManageComponent,
    RoleManageComponent,
    UserManageComponent,
    LoginComponent,
    MainComponent,
    AppComponent,
    ProCategoryEditFormComponent,
    UserEditFormComponent,
  ],
  imports: [
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
