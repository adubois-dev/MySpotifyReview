package fr.spotify.review.restservice;

import fr.spotify.review.domain.OutputMostPlayed;

import java.util.ArrayList;

public record UserMostPlayed (ArrayList<OutputMostPlayed> returnInstance) { }

