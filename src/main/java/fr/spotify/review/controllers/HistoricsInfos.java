package fr.spotify.review.controllers;

import fr.spotify.review.domain.OutputAllHistorics;

import java.util.ArrayList;

public record HistoricsInfos(ArrayList<OutputAllHistorics> histosArray) { }

