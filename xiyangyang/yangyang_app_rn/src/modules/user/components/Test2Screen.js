import React from 'react';
import {
  ActivityIndicator,
  AsyncStorage,
  StyleSheet,
  View,Text,Button
} from 'react-native';
import { BaseScreenComponent } from '../../../core/BaseScreenComponent';

export class Test2Screen extends BaseScreenComponent {
  
  constructor(props) {
    super(props);
  }

  // Render any loading content that you like here
  render() {
    return (
      <View style={styles.container}>
        <Text>test2</Text>
      </View>
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