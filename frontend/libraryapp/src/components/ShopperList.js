import { Component } from "react";

class ShopperList extends Component{
    constructor(props) {
        super(props);
        this.state = {clients: []};
        this.remove = this.remove.bind(this);
        
    }
}