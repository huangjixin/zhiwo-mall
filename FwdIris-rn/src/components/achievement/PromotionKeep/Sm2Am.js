import React from 'react';
import {StyleSheet, ScrollView,View, Text} from 'react-native';
import {AchvPromTarget} from '../AchvPromTarget'

export class Sm2Am extends React.Component {
  render() {
    return (
        <View style={{flexDirection: 'column',}}>
            <View style={{flexDirection: 'row'}}>
            <AchvPromTarget
                percentage={100}
                itemTitl={'个人FYC业绩'}
                itemValue={'65万'}
                target={'60万'}
                balance={'0'}
                isComplete={true}
            />

            <AchvPromTarget
                percentage={100}
                itemTitl={'个人K1续保率'}
                itemValue={'95.12%'}
                target={'90%'}
                balance={'0'}
                isComplete={true}
            />
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

