import React from 'react';
import {connect} from 'react-redux';
import {
  ActivityIndicator,
  AsyncStorage,
  StatusBar,
  StyleSheet,
  View,Text
} from 'react-native';

import NavigationService from './NavigationService';
import NavigationConstants from './NavigationConstants';
import {SwitchNavigation} from './RootSwitchNavigation';

/**
 * 黄记新（Tony） 2018.07.20
 * 该类是根类用于导航。
 */
export class RootNavigation extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
          <SwitchNavigation ref={navigatorRef => {
            NavigationService.setTopLevelNavigator(navigatorRef);
          }}></SwitchNavigation>
        );
    }
}

export default RootNavigation;