import Page from "../../../core/Page";
import IndexConstants from "../constants/IndexConstants";
import Response from "../../../core/Response";

'use strict';

// 初始状态
const initialState = {
    status:'',
    response:new Response()
}

// 不同类别的事件使用switch对应处理过程
const IndexReducer = (state=initialState, action)=> {
    switch (action.type) {
        case IndexConstants.INDEX_DOING:
            return {
                ...state,
                status:IndexConstants.INDEX_DOING
            }
            break;
        case IndexConstants.INDEX_DONE:
            return {
                ...state,
                status:IndexConstants.INDEX_DONE,
                response:action.response,
            }
            break;
        case IndexConstants.INDEX_ERROR:
            return {
                ...state,
                status:IndexConstants.INDEX_ERROR,
                response:action.response,
            }
            break;
        default:
            return state;
    }
}

export default IndexReducer;