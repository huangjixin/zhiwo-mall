import React from 'react';
import {StyleSheet, Text ,View,TouchableWithoutFeedback} from 'react-native';
export class MyApproval extends React.Component {
    onExaminationAndApproval=()=>{
        //alert('审批通过');
    }
    inExaminationAndApproval=()=>{
        //alert('审批中');
    }
    render() {
        return (
            <View>
                <View style={{flexDirection: 'row',justifyContent:'space-around',backgroundColor: '#F2F2F2',alignItems: 'center',}} >
                    <TouchableWithoutFeedback onPress={this.onExaminationAndApproval }>
                        <Text style={{backgroundColor:'#F8F8F8',marginTop:5,marginBottom:5,borderWidth:0.5,borderColor:'#CCCCCC',height:40,paddingTop:8,paddingLeft:20,paddingRight:20,fontSize:18}}>审批通过</Text>
                    </TouchableWithoutFeedback>
                    <TouchableWithoutFeedback onPress={this.inExaminationAndApproval}>
                        <Text style={{backgroundColor:'#F8F8F8',marginTop:5,marginBottom:5,borderWidth:0.5,borderColor:'#CCCCCC',height:40,paddingTop:8,paddingLeft:20,paddingRight:20,fontSize:18}}>审批中</Text>
                    </TouchableWithoutFeedback>
                </View>
                <View style={{backgroundColor:'white',borderRadius:5,
                    borderColor:'#CCCCCC',borderWidth:0.5,border:'sold',marginLeft:1,marginRight:1,paddingBottom:10,paddingTop:10,}}>
                    <View style={{flexDirection: 'row',borderBottomWidth:0.5,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',marginLeft:10,marginRight:10,}}>
                        <Text style={{marginBottom:10,fontSize:15,flex:8,}}>请假申请（1天）</Text>
                        <Text style={{marginBottom:10,fontSize:15,flex:2,paddingLeft:50,}}>调休</Text>
                    </View>
                    <View style={{flexDirection: 'row',}}>
                        <View style={{flex:8,marginLeft:20,}}>
                            <Text style={styles.detailText}>时间：2018-01-12 09:00  ~  2018-01-12 18:00</Text>
                            <Text style={styles.detailText}>事由：此处展示调休的事由</Text>
                        </View>
                        <View style={{flex:2,marginRight:15,marginTop:10,}}>
                            <View style={{borderColor:'#FFBC59',borderWidth:2,border:'sold',borderRadius:30,width:60,height:60,}}>
                                <Text style={{fontSize:18,marginLeft:2.5,marginTop:5,textAlign:'center',color:'#FFBC59'}}>我的审批</Text>
                            </View>
                        </View>
                    </View>
                </View>
            </View>


        );
    }
}
const styles = StyleSheet.create({
    container: {flex: 1,},
    detailText:{marginTop:10,},
});