<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

    <head>

        <meta charset="utf-8" />

        <title>AjoutStation</title>

        <link type="text/css" rel="stylesheet" href="form.css" />

    </head>

    <body>

        <form method="post" action="ajoutstation">

            <fieldset>

                <legend>Ajout de Station</legend>

                <p>Vous pouvez ajouter une station via ce formulaire.</p>


                <label for="nom">Nom de la station <span class="requis">*</span></label>

                <input type="text" id="nom" name="nom" value="<c:out value="${station.nom}"/>" size="20" maxlength="60" />

                <span class="erreur">${form.erreurs['nom']}</span>

                <br />


                <label for="adresse">Adresse <span class="requis">*</span></label>

                <input type="text" id="adresse" name="adresse" value="<c:out value="${station.adresse}"/>" size="20" maxlength="20" />

                <span class="erreur">${form.erreurs['adresse']}</span>

                <br />


                <label for="ville">Ville <span class="requis">*</span></label>

                <input type="text" id="ville" name="ville" value="<c:out value="${station.ville}"/>" size="20" maxlength="20" />

                <span class="erreur">${form.erreurs['ville']}</span>

                <br />


                <label for="codePostal">Code Postal</label>

                <input type="text" id="codePostal" name="codePostal" value="<c:out value="${utilisateur.codePostal}"/>" size="20" maxlength="20" />

                <span class="erreur">${form.erreurs['codePostal']}</span>

                <br />


				<label for="codePostal">Nombre de places</label>

                <input type="text" id="nbPlaces" name="nbPlaces" value="<c:out value="${utilisateur.nbPlaces}"/>" size="20" maxlength="2" />

                <span class="erreur">${form.erreurs['nbPlaces']}</span>

                <br />
                
                

                <input type="submit" value="AjoutStation" class="sansLabel" />

                <br />

                

                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

            </fieldset>

        </form>

    </body>

</html>