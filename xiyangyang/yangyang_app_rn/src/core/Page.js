export default class Page {
    get currentPage() {
        if(this._currentPage === null || this._currentPage === undefined){
            this._currentPage  = 1;
        }
        return this._currentPage
    }
      
    set currentPage(currentPage) {
        this._currentPage = currentPage
    }

    get size() {
        if(this._size === null || this._size === undefined){
            this._size  = 10;
        }
        return this._size
    }
      
    set size(size) {
        this._size = size;
    }

    constructor(){
        // if(page!=null && page!=undefined){
        //     if(page.hasOwnProperty('currentPage')){
        //         this.currentPage = page.currentPage;
        //     }else{
        //         this.currentPage = 1;
        //     }
        // }else{
        //     this.currentPage = 1;
        // }

        // if(page!=null && page!=undefined){
        //     if(page.hasOwnProperty('size')){
        //         this.size = page.size;
        //     }else{
        //         this.size = 10;
        //     }
        // }else{
        //     this.size = 10;
        // }
    }
}