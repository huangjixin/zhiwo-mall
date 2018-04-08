import { Component, OnInit, ViewChild } from '@angular/core';
import { WeUITabBarItem, WeUITabBar } from 'angular4-weui';
import { SwiperComponent, SwiperDirective, SwiperConfigInterface,
  SwiperScrollbarInterface, SwiperPaginationInterface } from 'ngx-swiper-wrapper';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  public index: number = 0;
  public disabled: Boolean = false;
  @ViewChild('weuiTabbar')
  weuiTabbar: WeUITabBar;

  public config: SwiperConfigInterface = {
    direction: 'horizontal',
    slidesPerView: 1,
    keyboard: false,
    mousewheel: false,
    scrollbar: false,
    navigation: false,
    pagination: false,
    allowTouchMove: false
  };

  constructor() { }

  ngOnInit() {

  }

  public onIndexChange(index: number): void {
    if( this.index == index ){
      return;
    }
    this.index = index;
    console.log('Swiper index: ', index);
    this.weuiTabbar.activeIndex = this.index;
  }

  onActivated(item: WeUITabBarItem): void {
    if( this.index == item.value ){
      return;
    }
    this.index = item.value;
    console.log(`当前选中的是：${item.value}`);
  }
}
