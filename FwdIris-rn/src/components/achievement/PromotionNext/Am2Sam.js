import React from 'react';
import {StyleSheet, ScrollView,View, Text} from 'react-native';
import {AchvPromTarget} from '../AchvPromTarget'

export class Am2Sam extends React.Component {
  render() {

    return (

        <View style={{flexDirection: 'column',}}>
        <View style={{flexDirection: 'row'}}>
            <AchvPromTarget
                percentage={100}
                itemTitl={'直辖组FYC'}
                itemValue={'20万'}
                target={'18万'}
                balance={'0'}
                isComplete={true}
            />

            <AchvPromTarget
                percentage={100}
                itemTitl={'直辖组活动人次'}
                itemValue={'24'}
                target={'24'}
                balance={'0'}
                isComplete={true}
            />
        </View>
            <View style={{flexDirection: 'row'}}>
            <AchvPromTarget
                percentage={100}
                itemTitl={'直辖有效人力'}
                itemValue={'7'}
                target={'7'}
                balance={'0'}
                isComplete={true}
            />


            <AchvPromTarget
                percentage={100}
                itemTitl={'直接育成主管招募有效新人'}
                itemValue={'2'}
                target={'2'}
                balance={'0'}
                isComplete={true}
            />
            </View>
            <View style={{flexDirection: 'row'}}>
            <AchvPromTarget
                percentage={100}
                itemTitl={'直辖人K1续保费'}
                itemValue={'90%'}
                target={'90%'}
                balance={'0'}
                isComplete={false}
            />

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

