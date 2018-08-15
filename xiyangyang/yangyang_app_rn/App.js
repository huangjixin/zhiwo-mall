import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import {Provider,connect} from 'react-redux';

import configureStore from './src/store/configStore';
import LoginComponent from './src/modules/user/components/LoginComponent';
import RootNavigation from './src/navigation/RootNavigation';
// import  SignInScreen  from './src/modules/home/components/SignInScreen';
import {SwitchNavigation} from './src/navigation/RootSwitchNavigation';
import { reduxifyNavigator } from 'react-navigation-redux-helpers';
import NavigationService from './src/navigation/NavigationService';
import {YellowBox} from 'react-native';

if(!__DEV__){
  // console.disableYellowBox = true;
}
console.disableYellowBox = true;
// debugger
// const AppNavi = reduxifyNavigator(SwitchNavigation, "root");


const store = configureStore();
// const mapStateToProps = (state) => ({
//   state: state.nav,
// });
// const AppWithNavigationState = connect(mapStateToProps)(AppNavi);

export default class App extends React.Component {
  render() {
    return (
      <Provider store = {store}>
        {/* <SwitchNavigation></SwitchNavigation> */}
        <RootNavigation ></RootNavigation>
      {/* <TestComponent></TestComponent> */}
        {/* <SignInScreen></SignInScreen> */}
        {/* <AppWithNavigationState ref={navigatorRef => {
          NavigationService.setTopLevelNavigator(navigatorRef);
        }}></AppWithNavigationState> */}
        {/* <LoginComponent></LoginComponent> */}
        {/* <View style={styles.container}>
          <Text>Open up App.js to start working on your app!</Text>
          <Text>Changes you make will automatically reload.</Text>
          <Text>Shake your phone to open the developer menu.</Text>
          <Text>My first react.</Text>
        </View> */}
      </Provider>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
