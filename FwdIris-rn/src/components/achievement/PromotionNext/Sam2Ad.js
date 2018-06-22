import React from 'react';
import {StyleSheet, ScrollView,View, Text} from 'react-native';
import {AchvPromTarget} from '../AchvPromTarget'

export class Sam2Ad extends React.Component {
  render() {
    return (
        <View style={{flexDirection: 'column',}}>
            <View style={{flexDirection: 'row'}}>
            <AchvPromTarget
                percentage={100}
                itemTitl={'任职满12月'}
                itemValue={'12'}
                target={'12'}
                balance={'0'}
                isComplete={true}
            />

            <AchvPromTarget
                percentage={100}
                itemTitl={'营业部FYC'}
                itemValue={'150万'}
                target={'180万'}
                balance={'30万'}
                isComplete={true}
            />
            </View>
            <View style={{flexDirection: 'row'}}>
            <AchvPromTarget
                percentage={100}
                itemTitl={'营业部活动'}
                itemValue={'7'}
                target={'7'}
                balance={'0'}
                isComplete={true}
            />


            <AchvPromTarget
                percentage={100}
                itemTitl={'营业部主管数'}
                itemValue={'10'}
                target={'10'}
                balance={'0'}
                isComplete={true}
            />
            </View>
            <View style={{flexDirection: 'row'}}>
            <AchvPromTarget
                percentage={70}
                itemTitl={'直接育成主管'}
                itemValue={'4'}
                target={'5'}
                balance={'1'}
                isComplete={false}
            />

            <AchvPromTarget
                percentage={100}
                itemTitl={'直辖人K1续保率'}
                itemValue={'90%'}
                target={'90%'}
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

