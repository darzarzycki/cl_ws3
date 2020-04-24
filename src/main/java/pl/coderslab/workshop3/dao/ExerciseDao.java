package pl.coderslab.workshop3.dao;

import pl.coderslab.workshop3.db.DbUtil;
import pl.coderslab.workshop3.model.Exercise;

import java.sql.*;
import java.util.Arrays;

public class ExerciseDao {
    private static final String CREATE_EXERCISE =
            "INSERT INTO exercises(title, description) VALUES (?, ?)";
    private static final String READ_EXERCISE =
            "SELECT * FROM exercises where id = ?";
    private static final String UPDATE_EXERCISE =
            "UPDATE exercises SET title = ?, description = ? where id = ?";
    private static final String DELETE_EXERCISE =
            "DELETE FROM exercises WHERE id = ?";
    private static final String FIND_EXERCISE =
            "SELECT * FROM exercises";
    private static final String FIND_EXERCISES_WITHOUT_SOLUTIONS =
            "SELECT * FROM exercises WHERE NOT EXISTS ( SELECT * FROM solutions WHERE solutions.exercise_id = exercises.id AND solutions.user_id = ? )";


    public Exercise createExercise(Exercise exercise) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_EXERCISE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                exercise.setId(resultSet.getInt(1));
            }
            return exercise;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Exercise readExercise(int exerciseId) {
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(READ_EXERCISE);
            statement.setInt(1,exerciseId);
            ResultSet resultSet =statement.executeQuery();
            if (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateExercise(Exercise exercise) {
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(UPDATE_EXERCISE);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.setInt(3, exercise.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteExercise(int exerciseId) {
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(DELETE_EXERCISE);
            statement.setInt(1, exerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Exercise[] addToArray(Exercise e, Exercise[] exercises) {
        Exercise[] tempExe = Arrays.copyOf(exercises, exercises.length +1);
        tempExe[exercises.length] = e;
        return tempExe;
    }
    public Exercise[] findAllExercises() {
        try (Connection conn = DbUtil.getConnection()) {
            Exercise[] exercises = new Exercise[0];
            PreparedStatement statement = conn.prepareStatement(FIND_EXERCISE);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                exercises = addToArray(exercise, exercises);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Exercise[] findWithoutSolution(int UserId) {
        try (Connection conn = DbUtil.getConnection()) {
            Exercise[] exercises = new Exercise[0];
            PreparedStatement statement = conn.prepareStatement(FIND_EXERCISES_WITHOUT_SOLUTIONS);
            statement.setInt(1, UserId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                exercises = addToArray(exercise, exercises);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
