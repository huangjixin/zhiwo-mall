import {DetachedRouteHandle, ActivatedRouteSnapshot, RouteReuseStrategy} from '@angular/router';

// This impl. bases upon one that can be found in the router's test cases.
export class SimpleReuseStrategy implements RouteReuseStrategy {

  public static handlers: { [key: string]: DetachedRouteHandle } = {};

  private static waitDelete: string;


  public static deleteRouteSnapshot(name: string): void {
    if (SimpleReuseStrategy.handlers[name]) {
      delete SimpleReuseStrategy.handlers[name];
    } else {
      SimpleReuseStrategy.waitDelete = name;
    }
  }

  /** 表示对所有路由允许复用 如果你有路由不想利用可以在这加一些业务逻辑判断 */
  public shouldDetach(route: ActivatedRouteSnapshot): boolean {
    const index: number = route.routeConfig.path.indexOf('edit/:id');
    if (index > -1) {
      return false;
    }
    return true;
  }

  /** 当路由离开时会触发。按path作为key存储路由快照&组件当前实例对象 */
  public store(route: ActivatedRouteSnapshot, handle: DetachedRouteHandle): void {
    if (SimpleReuseStrategy.waitDelete && SimpleReuseStrategy.waitDelete === this.getRouteUrl(route)) {
      // 如果待删除是当前路由则不存储快照
      SimpleReuseStrategy.waitDelete = null;
      return;
    }
    const index: number = route.routeConfig.path.indexOf('edit/:id');
    if (index > -1) {
      return;
    }

    SimpleReuseStrategy.handlers[this.getRouteUrl(route)] = handle;
  }

  /** 若 path 在缓存中有的都认为允许还原路由 */
  public shouldAttach(route: ActivatedRouteSnapshot): boolean {
    return !!SimpleReuseStrategy.handlers[this.getRouteUrl(route)];
  }

  /** 从缓存中获取快照，若无则返回nul */
  public retrieve(route: ActivatedRouteSnapshot): DetachedRouteHandle {
    if (!route.routeConfig) {
      return null;
    }

    return SimpleReuseStrategy.handlers[this.getRouteUrl(route)];
  }

  /** 进入路由触发，判断是否同一路由 */
  public shouldReuseRoute(future: ActivatedRouteSnapshot, curr: ActivatedRouteSnapshot): boolean {
    return future.routeConfig === curr.routeConfig &&
      JSON.stringify(future.params) === JSON.stringify(curr.params);
  }

  private getRouteUrl(route: ActivatedRouteSnapshot) {
    return route['_routerState'].url.replace(/\//g, '_');
  }


 /* handlers: {[key: string]: DetachedRouteHandle} = {};
  /!** 表示对所有路由允许复用 如果你有路由不想利用可以在这加一些业务逻辑判断 *!/
  shouldDetach(route: ActivatedRouteSnapshot): boolean {
    console.log('CustomReuseStrategy:shouldDetach', route);
    return true;
  }
  /!** 当路由离开时会触发。按path作为key存储路由快照&组件当前实例对象 *!/
  store(route: ActivatedRouteSnapshot, handle: DetachedRouteHandle): void {
    console.log('CustomReuseStrategy:store', route, handle);
    this.handlers[route.routeConfig.path] = handle;
  }
  /!** 若 path 在缓存中有的都认为允许还原路由 *!/
  shouldAttach(route: ActivatedRouteSnapshot): boolean {
    console.log('CustomReuseStrategy:shouldAttach', route);
    return !!route.routeConfig && !!this.handlers[route.routeConfig.path];
  }
  /!** 从缓存中获取快照，若无则返回nul *!/
  retrieve(route: ActivatedRouteSnapshot): DetachedRouteHandle {
    console.log('CustomReuseStrategy:retrieve', route);
    if (!route.routeConfig) {
      return null;
    }
    return this.handlers[route.routeConfig.path];
  }
  /!** 进入路由触发，判断是否同一路由 *!/
  shouldReuseRoute(future: ActivatedRouteSnapshot, curr: ActivatedRouteSnapshot): boolean {
    console.log('CustomReuseStrategy:shouldReuseRoute', future, curr);
    return future.routeConfig === curr.routeConfig &&
      JSON.stringify(future.params) === JSON.stringify(curr.params);
  }*/
}