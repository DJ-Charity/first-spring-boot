import React, { Component } from 'react';
class App extends Component {
  state = {
    shoppers: []
  };

  async componentDidMount() {
    const response = await fetch('/first');
    const body = await response.json();
    this.setState({shoppers: body});
  }

  render() {
    const {shoppers} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            <div className="App-intro">
              <h2>Shoppers</h2>
              {shoppers.map(shoppers =>
                  <div key={shoppers.id}>
                    {shoppers.username} ({shoppers.email})
                  </div>
              )}
            </div>
          </header>
        </div>
    );
  }
}
export default App;