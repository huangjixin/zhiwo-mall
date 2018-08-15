import Page from "./Page";

export default class Response {
    
    get page() {
        if(this._page === null || this._page === undefined){
            this._page  = new Page();
        }
        return this._page
    }
      
    set page(page) {
        this._page = page;
    }

    get code() {
        if(this._code === null || this._code === undefined){
            this._code  = '';
        }

        return this._code
    }
      
    set code(code) {
        this._code = code;
    }
    
    get message() {
        if(this._message === null || this._message === undefined){
            this._message  = '';
        }

        return this._message
    }
      
    set message(message) {
        this._message = message;
    }

    get data() {
        if(this._data === null || this._data === undefined){
            this._data  = {};
        }

        return this._data
    }
      
    set data(data) {
        this._data = data;
    }
    
    get dataList() {
        if(this._dataList === null || this._dataList === undefined){
            this._dataList  = new Array();
        }

        return this._dataList
    }
      
    set dataList(dataList) {
        this._dataList = dataList;
    }

    constructor(){
        this._dataList  = new Array();
        this._data  = {};
        this._message  = '';
        this._code  = '';
        this._page  = new Page();
    }
}