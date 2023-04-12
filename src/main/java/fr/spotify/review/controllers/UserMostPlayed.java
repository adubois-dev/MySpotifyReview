package fr.spotify.review.controllers;

import fr.spotify.review.domain.OutputMostPlayed;

import java.util.ArrayList;

public record UserMostPlayed (ArrayList<OutputMostPlayed> returnInstance) { }

