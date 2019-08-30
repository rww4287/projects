'use strict';
module.exports = (sequelize, DataTypes) => {
  const movie = sequelize.define('movie', {
    name: DataTypes.STRING,
    year: DataTypes.INTEGER,
    director: DataTypes.STRING,
    poster: DataTypes.STRING
  }, {});
  movie.associate = function(models) {
    // associations can be defined here
   movie.hasMany(models.review, {foreignKey: 'movieId'});
  };
  return movie;
};