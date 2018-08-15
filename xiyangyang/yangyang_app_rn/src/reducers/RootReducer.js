import {combineReducers} from 'redux';
import LoginReducer from '../modules/user/reducers/LoginReducer';
import SignInReducer from '../modules/home/reducers/SignInReducer';
import {SwitchNavigation} from '../navigation/RootSwitchNavigation';
import IndexReducer from '../modules/index/reducers/IndexReducer';
// import {
//     reduxifyNavigator,
//     createReactNavigationReduxMiddleware,
//     createNavigationReducer,
//   } from 'react-navigation-redux-helpers';

/**
 * 作者：黄记新（Tony）
 * 2018.07.18
 * 联合Reducer.
 */
// const navReducer = createNavigationReducer(SwitchNavigation);

const RootReducer = combineReducers({
    // nav: navReducer,
    loginReducer:LoginReducer,
    signInReducer:SignInReducer,
    indexReducer:IndexReducer,
});

export default RootReducer;