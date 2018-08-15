'use strict';
import {
	createStore,
	applyMiddleware
} from 'redux';
import thunkMiddleware from 'redux-thunk';
import RootReducer from '../reducers/RootReducer';
// import promiseMiddleware from 'redux-promise-middleware';
import {
	createReactNavigationReduxMiddleware
  } from 'react-navigation-redux-helpers';

// 日志中间件。
const loggerMiddleware = store => next => action => {
	if(typeof action === 'function') {
		console.log('dispatching a function');
	} else {
		console.log('dispatching', action);
	}

	let result = next(action);
	console.log('next state', store.getState());
	return result;
}

// redux中间件。
// const reduxMiddleware = createReactNavigationReduxMiddleware(
// 	"root",
// 	state => state.nav,
//   );
//   promiseMiddleware(),
const createStoreWithMiddleware = applyMiddleware(
	thunkMiddleware,loggerMiddleware)(createStore);

export default function configureStore(initialState) {
	const store = createStoreWithMiddleware(RootReducer, initialState);
	
	return store;

}