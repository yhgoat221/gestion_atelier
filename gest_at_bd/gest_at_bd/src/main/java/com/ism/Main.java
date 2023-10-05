package com.ism;

import java.util.Scanner;

import com.ism.entities.ArticleConfection;
import com.ism.entities.Categorie;
import com.ism.repository.ITables;
import com.ism.repository.list.TableArticleConfection;
import com.ism.repository.list.TableCategories;
import com.ism.services.ArticleConfectionService;
import com.ism.services.ArticleConfectionServiceImpl;
import com.ism.services.CategorieService;
import com.ism.services.CategorieServiceImpl;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        ITables<Categorie> categorieRepository = new TableCategories();
        CategorieService categorieService = new CategorieServiceImpl(categorieRepository);
        ITables<ArticleConfection> articleConfectionRepository = new TableArticleConfection();
        ArticleConfectionService articleConfectionService = new ArticleConfectionServiceImpl(articleConfectionRepository);

        int choice;

        do {
            System.out.println("-------MENU GENERAL-------");
            System.out.println("1----Gestion des catégories-----");
            System.out.println("2----Gestion des articles de confection------");
            System.out.println("3----Quitter------");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    gestionCategories(categorieService);
                    break;
                case 2:
                    gestionArticlesConfection(articleConfectionService);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Option invalide !");
                    break;
            }
        } while (choice != 3);
    }

    public static void gestionCategories(CategorieService categorieService) {
        int choice;

        do {
            System.out.println("-------MENU GESTION DES CATEGORIES-------");
            System.out.println("1----Ajouter une catégorie-----");
            System.out.println("2----Lister les catégories------");
            System.out.println("3----Modifier une catégorie------");
            System.out.println("4----Supprimer une catégorie-----");
            System.out.println("5----Rechercher une catégorie----");
            System.out.println("6----Supprimer plusieurs catégories------");
            System.out.println("7----Retour au menu principal------");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Entrer le libellé de la catégorie :");
                    String libelle = scanner.nextLine();
                    Categorie newCategorie = new Categorie(libelle);
                    categorieService.add(newCategorie);
                    break;
                case 2:
                    System.out.println("Liste de toutes les catégories :");
                    categorieService.getAll().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Entrer l'ID de la catégorie à modifier :");
                    int categoryIdToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Entrer le nouveau libellé :");
                    String newLibelle = scanner.nextLine();
                    Categorie categorieToUpdate = new Categorie(newLibelle);
                    categorieToUpdate.setId(categoryIdToUpdate);
                    int updateResult = categorieService.update(categorieToUpdate);
                    if (updateResult >= 1) {
                        System.out.println("Catégorie mise à jour avec succès.");
                    } else {
                        System.out.println("La catégorie n'a pas pu être mise à jour.");
                    }
                    break;
                case 4:
                    System.out.println("Entrer l'ID de la catégorie à supprimer :");
                    int categoryIdToDelete = scanner.nextInt();
                    scanner.nextLine();
                    int deleteResult = categorieService.remove(categoryIdToDelete);
                    if (deleteResult == 1) {
                        System.out.println("Catégorie supprimée avec succès.");
                    } else {
                        System.out.println("La catégorie n'a pas pu être supprimée.");
                    }
                    break;
                case 5:
                    System.out.println("Entrer l'ID de la catégorie à afficher :");
                    int categoryIdToShow = scanner.nextInt();
                    scanner.nextLine();
                    Categorie categorieToShow = categorieService.show(categoryIdToShow);
                    if (categorieToShow != null) {
                        System.out.println("Catégorie : " + categorieToShow);
                    } else {
                        System.out.println("Catégorie non trouvée.");
                    }
                    break;
                case 6:
                    System.out.println("Entrer le nombre d'IDs à supprimer :");
                    int countToDelete = scanner.nextInt();
                    scanner.nextLine();
                    int[] idsToDelete = new int[countToDelete];
                    for (int i = 0; i < countToDelete; i++) {
                        System.out.println("Entrer l'ID " + (i + 1) + " à supprimer :");
                        idsToDelete[i] = scanner.nextInt();
                        scanner.nextLine();
                    }
                    int[] notDeletedIds = categorieService.remove(idsToDelete);
                    if (notDeletedIds.length == 0) {
                        System.out.println("Toutes les catégories ont été supprimées avec succès.");
                    } else {
                        System.out.println("Les catégories suivantes n'ont pas pu être supprimées :");
                        for (int id : notDeletedIds) {
                            System.out.println("ID " + id);
                        }
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Option invalide !");
                    break;
            }
        } while (choice != 7);
    }

    public static void gestionArticlesConfection(ArticleConfectionService articleConfectionService) {
        int choixArticle;

        do {
            System.out.println("\nMenu Articles de Confection");
            System.out.println("1- Ajouter article");
            System.out.println("2- Lister articles");
            System.out.println("3- Modifier un article");
            System.out.println("4- Supprimer un article");
            System.out.println("5- Supprimer plusieurs articles");
            System.out.println("6- Rechercher un article");
            System.out.println("7- Retour au menu principal");
            choixArticle = scanner.nextInt();
            scanner.nextLine();

            switch (choixArticle) {
                // case 1:
                //     System.out.println("Entrer le libellé de la catégorie :");
                //     String libelle = scanner.nextLine();
                //     System.out.println("Entrer le prix de l'article :");
                //     double prix = scanner.nextDouble();
                //     System.out.println("Entrer la qte de l'article :");
                //     double qte = scanner.nextDouble();
                //     ArticleConfection newArticleConfection = new ArticleConfection(libelle, prix, qte);
                //     articleConfectionService.add(newArticleConfection);
                //     break;

                case 2:
                    System.out.println("Liste de toutes les articles :");
                    articleConfectionService.getAll().forEach(System.out::println);
                    break;
                case 3:
                    // Add your code to modify an article here
                    break;
                case 4:
                    System.out.println("Entrer l'ID de l'article à supprimer :");
                    int articleIdToDelete = scanner.nextInt();
                    scanner.nextLine();
                    int deleteResult = articleConfectionService.remove(articleIdToDelete);
                    if (deleteResult == 1) {
                        System.out.println("Article supprimée avec succès.");
                    } else {
                        System.out.println("Article n'a pas pu être supprimée.");
                    }
                    break;
                case 5:
                    System.out.println("Entrer le nombre d'IDs à supprimer :");
                    int countToDelete = scanner.nextInt();
                    scanner.nextLine();
                    int[] idsToDelete = new int[countToDelete];
                    for (int i = 0; i < countToDelete; i++) {
                        System.out.println("Entrer l'ID " + (i + 1) + " à supprimer :");
                        idsToDelete[i] = scanner.nextInt();
                        scanner.nextLine();
                    }
                    int[] notDeletedIds = articleConfectionService.remove(idsToDelete);
                    if (notDeletedIds.length == 0) {
                        System.out.println("Tous les articles ont été supprimées avec succès.");
                    } else {
                        System.out.println("Les articles suivants n'ont pas pu être supprimées :");
                        for (int id : notDeletedIds) {
                            System.out.println("ID " + id);
                        }
                    }
                    break;
                case 6:
                    System.out.println("Entrer l'ID de l'article à afficher :");
                    int articleIdToShow = scanner.nextInt();
                    scanner.nextLine();
                    ArticleConfection articleToShow = articleConfectionService.show(articleIdToShow);
                    if (articleToShow != null) {
                        System.out.println("Article : " + articleToShow);
                    } else {
                        System.out.println("Article non trouvée.");
                    }
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Option invalide !");
                    break;
            }
        } while (choixArticle != 7);
    }
}
