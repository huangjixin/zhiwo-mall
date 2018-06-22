import React, { Component } from 'react';
import { AppRegistry, View,Text,StyleSheet} from 'react-native';
import { AnimatedCircularProgress } from 'react-native-circular-progress';

export class AchvDountChart extends Component {
  render() {
      const {
          itemType,
          itemValue
      } = this.props;

      return (

      <View style={styles.container}>
        <View style={{height:100}}>
            <AnimatedCircularProgress
                size={100}
                width={5}
                fill={75}
                rotation={0}
                tintColor="#4FBDD5"
                backgroundColor="#DCDCDC">
                {
                    (fill) => (
                        <View >
                            <Text style = {{textAlign:'center'}}>{itemType}</Text>
                            <Text style = {{fontSize:18,color:'#FFB207'}}>{itemValue}</Text>
                        </View>
                    )
                }
            </AnimatedCircularProgress>
        </View>

        <Text >目标：50,000</Text> 
        <Text >差额：20,000</Text> 
      </View>
    );
  }
};


const styles = StyleSheet.create({
  container :{
    flexDirection: 'column',
    height:150,
    flex:1,
    padding:5
  },

});