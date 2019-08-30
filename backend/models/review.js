'use strict';
module.exports = (sequelize, DataTypes) => {
  const review = sequelize.define('review', {
    review_writer: DataTypes.STRING,
    review_title: DataTypes.STRING,
    review_content: DataTypes.STRING,
    review_rate: DataTypes.INTEGER,
    movieId: DataTypes.INTEGER

  }, {});
  review.associate = function(models) {
    // associations can be defined here
    review.belongsTo(models.movie,{
      foreignKey: "movieId"
    })
  };
  return review;
};