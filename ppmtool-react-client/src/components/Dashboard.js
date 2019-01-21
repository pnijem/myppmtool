import React, { Component } from "react";
import Projectitem from "./Project/Projectitem";
import CreateProjectButton from "./Project/CreateProjectButton";
import { connect } from "react-redux";
import { getProjects } from "./../actions/projectActions";
import PropTypes from "prop-types";

class Dashboard extends Component {
  componentDidMount() {
    return this.state.getProjects;
  }

  render() {
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />
              <CreateProjectButton />
              <br />
              <hr />
              <Projectitem />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.propTypes = {
  project: PropTypes.object.isRequired,
  getProjects: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  project: state.project
});

export default connect(
  mapStateToProps,
  { getProjects }
)(Dashboard); //connect the component to the redux store
