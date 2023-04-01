package fr.spotify.review.restservice;

import fr.spotify.review.domain.Historics;
import fr.spotify.review.domain.OutputMostPlayed;

import java.sql.ResultSet;
import java.util.ArrayList;

public record UserMostPlayed (ArrayList<OutputMostPlayed> returnInstance) { }

