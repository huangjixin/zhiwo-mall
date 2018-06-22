import React from 'react';
import {StyleSheet, ScrollView,View, Text} from 'react-native';
import {AchvPromTarget} from '../AchvPromTarget'

export class La2Sm extends React.Component {
  render() {
    return (

        <View style={{flexDirection: 'column',}}>
            <View style={{flexDirection: 'row'}}>
            <AchvPromTarget
                percentage={100}
                itemTitl={'个人FYC'}
                itemValue={'2000'}
                target={'2000'}
                balance={'0'}
                isComplete={true}
            />
                <View style={{flex:1}}/>
            </View>
        </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});

