package fr.spotify.review.restservice;

import fr.spotify.review.domain.Historics;
import fr.spotify.review.domain.OutputAssessment;
import fr.spotify.review.domain.OutputMostPlayed;

import java.sql.ResultSet;
import java.util.ArrayList;

public record UserAssessment (OutputAssessment returnInstance) { }

