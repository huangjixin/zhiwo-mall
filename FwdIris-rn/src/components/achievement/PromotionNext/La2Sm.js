import React from 'react';
import {StyleSheet, ScrollView,View, Text} from 'react-native';
import {AchvPromTarget} from '../AchvPromTarget'

export class La2Sm extends React.Component {
  render() {
      const data = this.props.data;
      // alert(data[0].title)
    return (

        <View style={{flexDirection: 'column',}}>
            <View style={{flexDirection: 'row'}}>
            <AchvPromTarget
                percentage={100}
                itemTitl={'个人FYC业绩'}
                itemValue={'25万'}
                target={'20万'}
                balance={'0'}
                isComplete={true}
            />

            <AchvPromTarget
                percentage={100}
                itemTitl={'直辖人K1续保费'}
                itemValue={'90%'}
                target={'90%'}
                balance={'0'}
                isComplete={false}
            />
            </View>
            <View style={{flexDirection: 'row'}}>
            <AchvPromTarget
                percentage={100}
                itemTitl={'个人出席率'}
                itemValue={'85%'}
                target={'80%'}
                balance={'0'}
                isComplete={false}
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

