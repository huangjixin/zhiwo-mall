import React from 'react';
import {StyleSheet, ScrollView,View, Text,TouchableWithoutFeedback} from 'react-native';
export class AchvCollapsible extends React.Component {
    static defaultProps = {
        renderHeader:()=>{},
        renderBlock:()=>{},
        pressCallback:()=>{},
        data:null,
        isCollapsed:true
    };

    constructor(props){
        super(props);
        const collapsed = this.props.isCollapsed;
        this.state = {
            collapsed:collapsed
        };
    }

    componentWillReceiveProps(nextProps){
        this.setState({ collapsed: nextProps.isCollapsed });
    }

    render() {
        const {
            renderHeader,
            renderBlock,
            data,
            style,
            pressCallback,
        } = this.props;
        const collapsed = this.state.collapsed;

        return (
            <View style={style}>
                <TouchableWithoutFeedback  onPress={() => {
                    this.setState({ collapsed: !collapsed });
                    pressCallback();
                }}>
                    {
                        renderHeader(data,collapsed)
                    }
                </TouchableWithoutFeedback>
                {
                    !collapsed && renderBlock(data)
                }
            </View>
        );
    }
}

